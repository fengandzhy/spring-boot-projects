package org.zhouhy.springboot.project1.domain;

import lombok.Data;

@Data
public class Ztree {
    public int id;
    public int pId;
    public String name;
    public boolean open;
    public boolean checked;
}
