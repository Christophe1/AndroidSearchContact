package com.example.androidsearchcontact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

/**
 * Created by Chris on 04/04/2016.
 */
public class PhoneContactsAdapter extends BaseAdapter{

    private List<CallData> listdata=null;
    Context ctxt;
    LayoutInflater myInflater;

//    this is a constructor
    public PhoneContactsAdapter(List <CallData> calldata, Context c) {

        this.listdata=calldata;
        ctxt = c;
        myInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        Create the cell (view) and populate it with an element of the array
//        return null;

        final ViewHolder holder;
//   if convertView is null, that is, if the cell is empty, then
        if (convertView == null || convertView.getTag() == null) {

//            Inflate list_row.xml file into each cell ( Defined below )
//            We're calling each cell convertView
            convertView = myInflater.inflate(R.layout.list_row, null);

//          View Holder Object to contain list_row.xml file elements
//           We need to do this in case a cell is null
            holder = new ViewHolder();
            holder.contactname = (TextView) convertView.findViewById(R.id.textView_name);
            holder.calltype = (TextView) convertView.findViewById(R.id.textView_number);
//            holder.calldate = (TextView) convertView.findViewById(R.id.textView_calldate);
//            holder.callduration = (TextView) convertView.findViewById(R.id.textView_callduration);

//            Set holder with LayoutInflater
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        CallData calldatalist = listdata.get(position);
        String callnumber=calldatalist.getCallnumber();
        String calltype=calldatalist.getCalltype();
//        Date calldate= calldatalist.getCalldatetime();
//        String callduration=calldatalist.getCallduration();

//actually create physical things that we will see on the screen, in each cell.
        holder.callnumber.setText("Call Number: "+callnumber);
        holder.calltype.setText("Call Type: "+calltype);
//        holder.calldate.setText("Call Date: "+String.valueOf(calldate));
//        holder.callduration.setText("Duration: "+callduration);

        return convertView;
    }

//static class doesn't need reference of Outer class, nice and simple

    private static class ViewHolder {
        TextView callnumber;
        TextView calltype;
        TextView calldate;
        TextView callduration;

    }
}
