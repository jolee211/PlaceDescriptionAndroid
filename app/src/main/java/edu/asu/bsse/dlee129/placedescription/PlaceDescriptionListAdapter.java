package edu.asu.bsse.dlee129.placedescription;// Copyright 2021 David Lee

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author David Lee    mailto:dlee129@asu.edu
 * @version January 2021
 */
public class PlaceDescriptionListAdapter extends RecyclerView.Adapter<PlaceDescriptionListAdapter.PlaceDescriptionHolder> {
    private final ArrayList<PlaceDescription> placeDescriptionList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class PlaceDescriptionHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public PlaceDescriptionHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

    public PlaceDescriptionListAdapter(ArrayList<PlaceDescription> placeDescriptionList) {
        this.placeDescriptionList = placeDescriptionList;
    }

    @NonNull
    @Override
    public PlaceDescriptionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_description_item, parent, false);
        return new PlaceDescriptionHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceDescriptionHolder holder, int position) {
        PlaceDescription currentItem = placeDescriptionList.get(position);
        holder.mImageView.setImageResource(R.drawable.ic_android);
        holder.mTextView1.setText(currentItem.getName());
        holder.mTextView2.setText(currentItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return placeDescriptionList.size();
    }
}
