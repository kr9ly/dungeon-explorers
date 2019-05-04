// textures
uniform sampler2D depthid;

varying vec2 tex;

#define PI    3.14159265

float width = 640; //texture width
float height = 1000; //texture height

float near = 1.0; //Z-near
float far = 3000.0; //Z-far

int samples = 5; //samples on the first ring (4-8)
int rings = 3; //ring count (3-6)

vec2 rand(in vec2 coord)
{
    float noiseX = (fract(sin(dot(coord ,vec2(12.9898,78.233))) * 43758.5453));
    float noiseY = (fract(sin(dot(coord ,vec2(12.9898,78.233)*2.0)) * 43758.5453));
    return vec2(noiseX,noiseY)*0.004;
}

float readDepth(in vec2 coord)
{
    return (2.0 * near) / (far + near - texture2D(depthid, coord).x * (far-near));
}

float compareDepths( in float depth1, in float depth2 )
{
    float aoCap = 1.0;
    float aoMultiplier = 1000.0; // 100.0
    float depthTolerance = 0.0000;
    float aorange = 60.0;// units in space the AO effect extends to (this gets divided by the camera far range
    float diff = sqrt(clamp(1.0-(depth1-depth2) / (aorange/(far-near)),0.0,1.0));
    float ao = min(aoCap,max(0.0,depth1-depth2-depthTolerance) * aoMultiplier) * diff;
    return ao;
}

void main() {
    float depth = readDepth(tex);
    float d;

    float aspect = width/height;
    vec2 noise = rand(tex);

    float w = (1.0 / width)/clamp(depth,0.05,1.0)+(noise.x*(1.0-noise.x));
    float h = (1.0 / height)/clamp(depth,0.05,1.0)+(noise.y*(1.0-noise.y));

    float pw;
    float ph;

    float ao;
    float s;
    float fade = 1.0;

    for (int i = 0 ; i < rings; i += 1)
    {
        fade *= 0.5;
        for (int j = 0 ; j < samples*i; j += 1)
        {
            float step = PI*2.0 / (samples*float(i));
            pw = (cos(float(j)*step)*float(i));
            ph = (sin(float(j)*step)*float(i))*aspect;
            d = readDepth( vec2(tex.s+pw*w,tex.t+ph*h));
            ao += compareDepths(depth,d)*fade;
            s += 1.0*fade;
        }
    }

    ao /= s;
    ao = 1.0-ao*ao;

    gl_FragData[0] = vec4(ao, ao, ao, 1.0);
}