package gui.windows.nodes.shaderList;

import processing.core.PGraphics;
import gui.windows.nodes.*;

public class PremadeFilterFolder extends FolderNode {

    public PremadeFilterFolder(String path, FolderNode parent) {
        super(path, parent);

        ShaderListItem tvStatic = new ShaderListItem(path + "/tv static", this, "filters/tvStatic.glsl", true);
        children.add(tvStatic);
        tvStatic.children.add(new SliderNode(tvStatic.path + "/alpha", tvStatic, 0.9f, 0, 1, 0.01f, true));
        tvStatic.children.add(new SliderNode(tvStatic.path + "/pixelate", tvStatic, 1, 0, 1, 0.01f, true));
        tvStatic.children.add(new SliderNode(tvStatic.path + "/speed", tvStatic, 0.1f, 0, 1, 0.01f, false));
        tvStatic.children.add(new SliderNode(tvStatic.path + "/freqX", tvStatic, 0.1f, 0, 1, 0.01f, false));
        tvStatic.children.add(new SliderNode(tvStatic.path + "/freqY", tvStatic, 0.1f, 0, 1, 0.01f, false));
        tvStatic.children.add(new SliderNode(tvStatic.path + "/offset", tvStatic, 0.1f, 0, 1, 0.01f, false));
        tvStatic.children.add(new SliderNode(tvStatic.path + "/blackPoint", tvStatic, 0.0f, 0, 1, 0.01f, false));
        tvStatic.children.add(new SliderNode(tvStatic.path + "/whitePoint", tvStatic, 0.9f, 0, 1, 0.01f, false));


        ShaderListItem noise = new ShaderListItem(path + "/noise", this, "filters/noise.glsl", true);
        noise.children.add(new SliderNode(noise.path + "/alpha", noise, 0.05f, 0, 1, 0.01f, true));
        noise.children.add(new SliderNode(noise.path + "/speed", noise, 0.1f, 0, 1, 0.01f, false));
        noise.children.add(new SliderNode(noise.path + "/baseValue", noise, 0.5f, 0, 1, 0.01f, false));
        noise.children.add(new SliderNode(noise.path + "/baseAmp", noise, 0.5f, 0, 1, 0.01f, false));
        noise.children.add(new SliderNode(noise.path + "/freqX", noise, 0.1f, 0, 1, 0.01f, false));
        noise.children.add(new SliderNode(noise.path + "/freqY", noise, 0.1f, 0, 1, 0.01f, false));
        noise.children.add(new SliderNode(noise.path + "/fbmFreqMult", noise, 2.0f, 0, 1, 0.01f, false));
        noise.children.add(new SliderNode(noise.path + "/fbmAmpMult", noise, 0.5f, 0, 1, 0.01f, false));
        noise.children.add(new SliderIntNode(noise.path + "/octaves", noise, 4, 0, 1, 0.01f, false));
        children.add(noise);

        ShaderListItem add = new ShaderListItem(path + "/add", this, "filters/addRGB.glsl", false);
        children.add(add);
        add.children.add(new SliderNode(add.path + "/deltaRGB", add, 0.0f, -1, 1, 0.01f, true));
        add.children.add(new SliderNode(add.path + "/deltaR", add, 0.0f, -1, 1, 0.01f, true));
        add.children.add(new SliderNode(add.path + "/deltaG", add, 0.0f, -1, 1, 0.01f, true));
        add.children.add(new SliderNode(add.path + "/deltaB", add, 0.0f, -1, 1, 0.01f, true));

        ShaderListItem flow = new ShaderListItem(path + "/flowFromCenter", this, "filters/flowFromCenter.glsl", false);
        children.add(flow);
        flow.children.add(new SliderNode(flow.path+"/delta", flow, 0.1f, 0, 1, 0.01f, false));

        ShaderListItem chromab = new ShaderListItem(path + "/chromab", this, "filters/chromab.glsl", false);
        children.add(chromab);
        chromab.children.add(new SliderNode(chromab.path + "/innerEdge", chromab, 0.0f, 0, 1, 0.01f, false));
        chromab.children.add(new SliderNode(chromab.path + "/outerEdge", chromab, 2.5f, 0, 1, 0.01f, false));
        chromab.children.add(new SliderNode(chromab.path + "/intensity", chromab, 1, 0, 1, 0.01f, false));
        chromab.children.add(new SliderNode(chromab.path + "/rotation", chromab, 0, 0, 1, 0.01f, false));
        chromab.children.add(new SliderIntNode(chromab.path + "/steps", chromab, 4, 0, 1, 1f, false));

        ShaderListItem fbmFlow = new ShaderListItem(path + "/flow", this, "filters/flow.glsl", true);
        fbmFlow.children.add(new SliderNode(fbmFlow.path + "/timeSpeed", fbmFlow, 0.11f));
        fbmFlow.children.add(new SliderNode(fbmFlow.path + "/angleOffset", fbmFlow, 0));
        fbmFlow.children.add(new SliderNode(fbmFlow.path + "/angleRange", fbmFlow, 8));
        fbmFlow.children.add(new SliderNode(fbmFlow.path + "/freq1", fbmFlow, 0.8f));
        fbmFlow.children.add(new SliderNode(fbmFlow.path + "/freq2", fbmFlow, 2));
        fbmFlow.children.add(new SliderNode(fbmFlow.path + "/freq3", fbmFlow, 4));
        fbmFlow.children.add(new SliderNode(fbmFlow.path + "/amp1", fbmFlow, 1));
        fbmFlow.children.add(new SliderNode(fbmFlow.path + "/amp2", fbmFlow, 0.5f));
        fbmFlow.children.add(new SliderNode(fbmFlow.path + "/amp3", fbmFlow, 0.25f));
        children.add(fbmFlow);

        ShaderListItem blur = new ShaderListItem(path + "/blur", this, "filters/blur.glsl", false);
        blur.children.add(new SliderNode(blur.path + "/scale", blur, 1));
        children.add(blur);

    }

    public void applyFilters(PGraphics pg) {
        for (AbstractNode child : children) {
            if (child.type == NodeType.FOLDER_ROW) {
                ShaderListItem childShader = (ShaderListItem) child;
                childShader.applyShader(pg, true);
            }
        }
    }

}