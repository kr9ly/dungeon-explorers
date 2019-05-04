// textures
uniform sampler2D texid;

uniform int texwidth;
uniform int texheight;

varying vec2 tex1;
varying vec2 tex2;
varying vec2 tex3;
varying vec2 tex4;
varying vec2 tex5;

void main() {

    vec4 col1 = texture2D(texid, tex1);
    vec4 col2 = texture2D(texid, tex2);
    vec4 col3 = texture2D(texid, tex3);
    vec4 col4 = texture2D(texid, tex4);
    vec4 col5 = texture2D(texid, tex5);

    gl_FragColor = (col1 + col2 + col3 + col4 + col5) / 5;
}