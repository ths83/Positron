package fr.univtln.groupc.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marti on 12/05/2016.
 */
public class CObjectList {
    private List<AObjectEntity> mList;

    public CObjectList(){
        mList = new ArrayList<>();
    }

    public void setList(List<AObjectEntity> pList){
        mList = pList;
    }

    public List<AObjectEntity> getList(){
        return mList;
    }
}
