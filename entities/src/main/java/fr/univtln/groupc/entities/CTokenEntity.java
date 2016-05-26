package fr.univtln.groupc.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by boblinux on 26/05/16.
 */

@Entity
@Table(name = "t_token", schema = "positron")
@NamedQueries({@NamedQuery(name = CTokenEntity.GET_ALL, query = "select p from CTokenEntity p")})

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CTokenEntity.class)

public class CTokenEntity implements Serializable {
    @Id
    @Column(name = "token")
    String mToken;

    public final static String GET_ALL = "Token.getAll";

    @Override
    public String toString() {
        return "CTokenEntity{" +
                "mToken='" + mToken + '\'' +
                '}';
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String mToken) {
        this.mToken = mToken;
    }

    public CTokenEntity(){}

    public CTokenEntity(String pToken){mToken = pToken;}
}
