attribute vec4 position;
attribute vec3 normal;
attribute vec4 color;
attribute vec2 uv;
attribute vec3 tangent;

uniform int texwidth;
uniform int texheight;

varying vec2 tex1;
varying vec2 tex2;
varying vec2 tex3;
varying vec2 tex4;
varying vec2 tex5;

void main(void)
{
    float xlength = 1.0 / texwidth;
    float ylength = 1.0 / texheight;

    gl_Position = position;
    tex1 = vec2(uv.x, 1.0 - uv.y);
    tex2 = vec2(uv.x + xlength, 1.0 - uv.y);
    tex3 = vec2(uv.x, 1.0 - uv.y + ylength);
    tex4 = vec2(uv.x - xlength, 1.0 - uv.y);
    tex5 = vec2(uv.x, 1.0 - uv.y - ylength);
}