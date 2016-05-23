package fr.univtln.groupc.activities.portals;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.univtln.m1dapm.groupec.tperron710.positron.R;

/**
 * Created by tperron710 on 19/05/16.
 */
public class CKeyAdapter extends ArrayAdapter<CKeyEntity> {

    public CKeyAdapter(Context pContext, List<CKeyEntity> pKeys){
        super(pContext, 0, pKeys);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_key, parent, false);
        }

        CKeyViewHolder viewHolder = (CKeyViewHolder) convertView.getTag();
        if (viewHolder == null){
            viewHolder = new CKeyViewHolder();
            viewHolder.mPortalId = (TextView) convertView.findViewById(R.id.rowKeyPortalId);
            convertView.setTag(viewHolder);
        }

        CKeyEntity lKey = getItem(position);

        Log.d("test", "dans adapter -> " + lKey.getId());
        viewHolder.mPortalId.setText(Integer.toString(lKey.getId()));
        return convertView;
    }


    private class CKeyViewHolder{
        public TextView mPortalId;
    }
}
