/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Model;

import java.util.Date;
import java.lang.*;
import java.util.*;
import java.io.*;
/**
 *
 * @author Phanh
 */
public abstract class BaseModel {
    private int id;
    private User createby;
    private Date createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCreateby() {
        return createby;
    }

    public void setCreateby(User createby) {
        this.createby = createby;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    
}
