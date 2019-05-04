// textures
uniform sampler2D texid;

varying vec4 col;
varying vec2 tex;

void main() {
    vec4 texcolor = texture(texid, tex);
    vec4 color = col * texcolor;

    gl_FragColor = color;
}