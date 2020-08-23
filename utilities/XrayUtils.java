// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.utilities;

import java.util.Iterator;
import java.util.ArrayList;

public class XrayUtils
{
    public ArrayList<XrayBlock> xrayBlocks;
    public boolean xrayEnabled;
    
    public XrayUtils() {
        this.xrayBlocks = new ArrayList();
        this.xrayEnabled = false;
    }
    
    public boolean shouldRenderBlock(final XrayBlock block) {
        for (final XrayBlock xBlock : this.xrayBlocks) {
            if (xBlock.getId() == block.getId()) {
                return true;
            }
        }
        return false;
    }
    
    public boolean shouldRenderBlock(final int block) {
        for (final XrayBlock xBlock : this.xrayBlocks) {
            if (xBlock.getId() == block) {
                return true;
            }
        }
        return false;
    }
    
    public boolean removeBlock(final int block) {
        for (final XrayBlock xBlock : this.xrayBlocks) {
            if (xBlock.getId() == block) {
                this.xrayBlocks.remove(this.xrayBlocks.indexOf(xBlock));
                return true;
            }
        }
        return false;
    }
    
    public boolean addBlock(final int block) {
        for (final XrayBlock xBlock : this.xrayBlocks) {
            if (xBlock.getId() == block) {
                return false;
            }
        }
        this.xrayBlocks.add(new XrayBlock(block));
        return true;
    }
}
