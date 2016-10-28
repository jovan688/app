package yio.io.sifaapp.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Stark on 30/06/2016.
 */
public interface OnStartDragListener {
    /**
     * Called when a view is requesting a start of a drag.
     *
     * @param viewHolder The holder of the view to drag.
     */
    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
