// textures
uniform sampler2D texid;
// bump map
uniform bool useBumpmap;
uniform sampler2D bumpid;

// lights
const int maxLights = 3;

struct Light {
    vec3 position;
    vec3 diffuse;
    float power;
};

uniform bool useLighting;
uniform Light lights[maxLights];

varying vec3 pos;
varying vec4 col;
varying vec3 nor;
varying vec2 tex;

varying vec3 tan;
varying vec3 bi;

void main() {
    vec3 diffuse = vec3(1.0, 1.0, 1.0);

    vec3 fnormal;
    if (useBumpmap) {
        vec4 normalColor = texture(bumpid, tex);
        fnormal = vec3(1.0 - normalColor.x, normalColor.y, normalColor.z) * 2.0 - 1.0;
    } else {
        fnormal.x = dot(nor, tan);
        fnormal.y = dot(nor, bi);
        fnormal.z = dot(nor, nor);
        fnormal = normalize(fnormal);
    }


    if (useLighting) {
        diffuse = vec3(0.0, 0.0, 0.0);
        for (int i = 0; i < maxLights; i++) {
            vec3 lvec = lights[i].position - pos;
            float length = length(lvec);
            vec3 flvec = normalize(lvec);

            vec3 light;
            light.x = dot(flvec, tan);
            light.y = dot(flvec, bi);
            light.z = dot(flvec, nor);
            light = normalize(light);

            diffuse += lights[i].diffuse * (1.0 - (length / lights[i].power)) * max(dot(light, fnormal), 0.0);
        }
        diffuse.x = min(diffuse.x, 1.0);
        diffuse.y = min(diffuse.y, 1.0);
        diffuse.z = min(diffuse.z, 1.0);
    }

    vec4 texcolor = texture(texid, tex);
    vec4 color = col * texcolor;

    gl_FragData[0] = vec4(color.xyz * diffuse, color.w);
    gl_FragData[1] = vec4(nor, 1.0);
}