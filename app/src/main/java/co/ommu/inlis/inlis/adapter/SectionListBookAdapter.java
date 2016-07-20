package co.ommu.inlis.inlis.adapter;

/**
 * Created by KurniawanD on 13-04-2016.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import co.ommu.inlis.BookDetailActivity;
import co.ommu.inlis.R;
import co.ommu.inlis.WebViewActivity;
import co.ommu.inlis.inlis.model.SingleBookItemModel;

import android.net.Uri;

public class SectionListBookAdapter extends RecyclerView.Adapter<SectionListBookAdapter.SingleItemRowHolder> {

    private ArrayList<SingleBookItemModel> itemsList;
    private Context mContext;

    public SectionListBookAdapter(Context context, ArrayList<SingleBookItemModel> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_single_book_card, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }


    @Override
    public void onBindViewHolder(final SingleItemRowHolder holder, int i) {

        final SingleBookItemModel singleItem = itemsList.get(i);

        holder.tvTitle.setText(singleItem.getName());
        holder.tvUrl.setText(singleItem.getUrl());

        if (i == itemsList.size() - 1)
            holder.line.setVisibility(View.GONE);
        else
            holder.line.setVisibility(View.VISIBLE);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (singleItem.getFrom() != 0) {

                    Intent intent;
                    switch (singleItem.getFrom()) {
                        case 2:
                            Uri uri = Uri.parse("geo:0,0?q=" + singleItem.getUrl());
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            v.getContext().startActivity(intent);
                            break;
                        case 3:


                            String[] listEmail = {singleItem.getUrl()};
                            intent = new Intent(Intent.ACTION_SENDTO);
                            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                            intent.putExtra(Intent.EXTRA_EMAIL, listEmail);
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Report a Problem");
                            if (intent.resolveActivity(v.getContext().getPackageManager()) != null) {
                                v.getContext().startActivity(intent);
                            }


                            break;
                        case 4:

                            final String appPackageName = v.getContext().getPackageName(); // getPackageName() from Context or Activity object

                            try {
                                v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                            } catch (android.content.ActivityNotFoundException anfe) {
                                v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                            }

                            break;

                    }


                } else {
                    v.getContext().startActivity(new Intent(v.getContext(), WebViewActivity.class)
                            .putExtra("url", holder.tvTitle.getText())
                            .putExtra("title", holder.tvUrl.getText())

                    );
                }


            }
        });


    }

    /* @Override
     public void onBindViewHolder(SingleItemRowHolder holder, int i) {

         SingleBookItemModel singleItem = itemsList.get(i);

         holder.tvTitle.setText(singleItem.getName());
         holder.tvUrl.setText(singleItem.getUrl());

         if (i == itemsList.size() - 1)
             holder.line.setVisibility(View.GONE);
         else
             holder.line.setVisibility(View.VISIBLE);


        *//* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)
                .into(feedListRowHolder.thumbView);*//*
    }
*/
    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitle, tvUrl;
        protected LinearLayout line;
        public final View mView;

        //protected ImageView itemImage;


        public SingleItemRowHolder(View view) {
            super(view);
            mView = view;
            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            this.line = (LinearLayout) view.findViewById(R.id.ll_line);
            this.tvUrl = (TextView) view.findViewById(R.id.tvUrl);
            //this.itemImage = (ImageView) view.findViewById(R.id.itemImage);

/*
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    v.getContext().startActivity(new Intent(v.getContext(), WebViewActivity.class)
                            .putExtra("url", tvUrl.getText())
                            .putExtra("title", tvTitle.getText())

                    );

                }
            });
*/

        }

    }

}