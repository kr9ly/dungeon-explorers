// textures
uniform sampler2D texid;
varying vec2 tex;

void main() {
    gl_FragColor = texture2D(texid, tex);
}