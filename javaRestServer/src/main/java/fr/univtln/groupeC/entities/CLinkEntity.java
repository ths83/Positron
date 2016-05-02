package fr.univtln.groupeC.entities;


import com.owlike.genson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by arouani277 on 26/04/16.
 */
@Entity
@Table(name = "t_link", schema = "positron")
public class CLinkEntity {
    @Id
    @Column(name = "link_id")
    private int mId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "field_fk")
    @JsonIgnore
    private CFieldEntity mField;
    //TODO  manytoone portal1 portal2

    public CLinkEntity() {}

    public CLinkEntity(CLinkBuilder pBuilder){
        mId = pBuilder.mId;
    }

    public static class CLinkBuilder{
        private int mId;

        public CLinkBuilder(int pId) {
            mId = pId;
        }

        public CLinkEntity build(){
            return new CLinkEntity((this));
        }
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public CFieldEntity getmField() {
        return mField;
    }

    public void setmField(CFieldEntity mField) {
        this.mField = mField;
    }
}