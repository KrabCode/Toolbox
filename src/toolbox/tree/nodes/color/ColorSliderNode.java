package toolbox.tree.nodes.color;

import com.jogamp.newt.event.MouseEvent;
import processing.core.PGraphics;
import processing.opengl.PShader;
import toolbox.ShaderStore;
import toolbox.tree.nodes.SliderNode;

import static processing.core.PApplet.norm;

public abstract class ColorSliderNode extends SliderNode {

    public final ColorPickerFolderNode parentColorPickerFolder;
    float maximumFloatPrecision = 0.1f;
    private final String colorShaderPath = "sliderBackgroundColor.glsl";
    protected int shaderColorMode = -1;

    public ColorSliderNode(String path, ColorPickerFolderNode parentFolder, float defaultValue) {
        super(path, parentFolder);
        this.parentColorPickerFolder = parentFolder;
        this.valueFloatDefault = 0;
        this.valueFloat = defaultValue;
        valueFloatPrecisionDefault = 0.01f;
        valueFloatPrecision = valueFloatPrecisionDefault;
        currentPrecisionIndex = precisionRange.indexOf(valueFloatPrecision);
        valueFloatConstrained = true;
        valueFloatMin = 0;
        valueFloatMax = 1;
        initSliderPrecisionArrays();
        initSliderBackgroundShader();
        ShaderStore.lazyInitGetShader(colorShaderPath);
    }


    @Override
    public void validatePrecision() {
        if (valueFloatPrecision >= maximumFloatPrecision) {
            valueFloatPrecision = maximumFloatPrecision;
            currentPrecisionIndex = precisionRange.indexOf(maximumFloatPrecision);
        }
    }

    @Override
    public void mouseDragNodeContinue(MouseEvent e, float x, float y, float px, float py) {
        super.mouseDragNodeContinue(e, x, y, px, py);
        updateColorInParentFolder();
        e.setConsumed(true);
    }

    @Override
    public void mouseReleasedOverNode(float x, float y) {
        super.mouseReleasedOverNode(x, y);
        updateColorInParentFolder();
    }

    abstract void updateColorInParentFolder();


    @Override
    protected void updateDrawBackgroundShader(PGraphics pg) {
        PShader shader = ShaderStore.lazyInitGetShader(colorShaderPath);
        shader.set("quadPos", pos.x, pos.y);
        shader.set("quadSize", size.x, size.y);
        shader.set("hueValue", parentColorPickerFolder.hue());
        shader.set("brightnessValue", parentColorPickerFolder.brightness());
        shader.set("saturationValue", parentColorPickerFolder.saturation());
        shader.set("alphaValue", parentColorPickerFolder.alpha());
        shader.set("mode", shaderColorMode);
        shader.set("precisionNormalized", norm(currentPrecisionIndex, 0, precisionRange.size()));
        ShaderStore.hotShader(colorShaderPath, pg);
    }
}