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

varying vec4 col;
varying vec2 tex;

void main(void)
{
    gl_Position = projection * (cameraRot * (modelRot * modelScale * position + vec4(modelPos.x, modelPos.y, modelPos.z, 0.0) - vec4(cameraPos.x, cameraPos.y, cameraPos.z, 0.0)));

    col = color;
    tex = uv;
}