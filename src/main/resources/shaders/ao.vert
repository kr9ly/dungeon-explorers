attribute vec4 position;
attribute vec3 normal;
attribute vec4 color;
attribute vec2 uv;
attribute vec3 tangent;

varying vec2 tex;

void main(void)
{
    gl_Position = position;
    tex = vec2(uv.x, 1.0 - uv.y);
}