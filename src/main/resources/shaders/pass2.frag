// textures
uniform sampler2D texid;
uniform sampler2D normalid;
uniform sampler2D depthid;
uniform sampler2D aoid;

varying vec2 tex;

void main() {
    gl_FragColor = texture2D(texid, tex) * texture2D(aoid, tex);
}