package ninjawar.model;

import java.util.List;
import ninjawar.mylib.mGraphics;
import ninjawar.mylib.mSystem;
import ninjawar.skill.EffectFrameBuffToEnd;
import ninjawar.skill.EffectShield;

public class EffectBuffStepByStep extends EffectShield {
    public EffectFrameBuffToEnd effCur;
    public List<EffectFrameBuffToEnd> listEff;

    public EffectBuffStepByStep(List<EffectFrameBuffToEnd> eff, int timeBuff) {
        this.timeBuff = mSystem.currentTimeMillis() + ((long) timeBuff);
        this.listEff = eff;
        if (eff != null && eff.size() > 1) {
            this.effCur = this.listEff.remove(0);
        }
    }

    public void update() {
        List<EffectFrameBuffToEnd> list;
        EffectFrameBuffToEnd effectFrameBuffToEnd = this.effCur;
        if (effectFrameBuffToEnd != null) {
            effectFrameBuffToEnd.update();
        }
        if (this.effCur == null && (list = this.listEff) != null && list.size() > 0) {
            this.effCur = this.listEff.remove(0);
        }
        EffectFrameBuffToEnd effectFrameBuffToEnd2 = this.effCur;
        if (effectFrameBuffToEnd2 != null && effectFrameBuffToEnd2.isDone) {
            this.effCur = null;
        }
        long j = this.timeBuff;
        if (j != -1 && j <= mSystem.currentTimeMillis()) {
            this.isRemove = true;
        }
    }

    public void paint(mGraphics g, int x, int y) {
        EffectFrameBuffToEnd effectFrameBuffToEnd = this.effCur;
        if (effectFrameBuffToEnd != null) {
            effectFrameBuffToEnd.paint(g, x, y);
        }
    }
}
