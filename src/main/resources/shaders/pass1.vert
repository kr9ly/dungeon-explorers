uniform mat4 projection;

uniform vec3 cameraPos;
uniform mat4 cameraRot;

uniform vec3 modelPos;
uniform mat4 modelRot;
uniform mat4 modelScale;

attribute vec4 position;
attribute vec3 normal;
attribute vec4 color;
attribute vec2 uv;
attribute vec3 tangent;

varying vec3 pos;
varying vec4 col;
varying vec3 nor;
varying vec2 tex;

varying vec3 tan;
varying vec3 bi;

void main(void)
{
    gl_Position = projection * (cameraRot * (modelRot * modelScale * position + vec4(modelPos.x, modelPos.y, modelPos.z, 0.0) - vec4(cameraPos.x, cameraPos.y, cameraPos.z, 0.0)));

    pos = modelPos + (modelRot * modelScale * position).xyz;
    col = color;
    nor = (modelRot * vec4(normal.x, normal.y, normal.z, 0.0)).xyz;
    tex = uv;

    // Send to FragmentShader for Bump Mapping
    tan = (modelRot * vec4(tangent.x, tangent.y, tangent.z, 0.0)).xyz;
    bi = cross(nor,tan);
}