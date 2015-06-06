package com.example.android4;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseExpandableListAdapter{
	
	private Context cont;
	private ArrayList<String> padre;
	private ArrayList<ArrayList<String>> hijos;
	private LayoutInflater infalInflater;

	public ListAdapter() {
		// TODO Auto-generated constructor stub
	}
	
	public ListAdapter(Context cont, ArrayList<String> diasPadre,
			ArrayList<ArrayList<String>> bloqueHijos) {
		super();
		this.cont = cont;
		this.padre = diasPadre;
		this.hijos = bloqueHijos;
		this.infalInflater = (LayoutInflater) this.cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public Object getChild(int posicionPadre, int posicionHijo) {
		// TODO Auto-generated method stub
		return hijos.get(posicionPadre).get(posicionHijo);
	}
	

	@Override
	public long getChildId(int posicionPadre, int posicionHijo) {
		// TODO Auto-generated method stub
		return posicionHijo;
	}

	@Override
	public View getChildView(int posicionPadre, int hijoPosicion,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if (convertView == null) {
            convertView = infalInflater.inflate(R.layout.hijo, null);
        }
		TextView tv = (TextView) convertView.findViewById(R.id.txvHijosHorario);
		tv.setText(hijos.get(posicionPadre).get(hijoPosicion));
		return convertView;
	}
	
	@Override
	public int getChildrenCount(int posicionPadre) {
		// TODO Auto-generated method stub
		return hijos.get(posicionPadre).size();
	}
	
	@Override
	public Object getGroup(int posicionPadre) {
		// TODO Auto-generated method stub
		return hijos.get(posicionPadre);
	}
	

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return hijos.size();
	}

	@Override
	public long getGroupId(int posicionPadre) {
		// TODO Auto-generated method stub
		return posicionPadre;
	}

	@Override
	public View getGroupView(int posicionPadre, boolean isExpanded, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if(convertView == null){
           convertView = infalInflater.inflate(R.layout.padre, null);
		}
		
		TextView tv = (TextView) convertView.findViewById(R.id.txvPadreHorario);
		tv.setTypeface(null, Typeface.BOLD);
		tv.setText("         " + padre.get(posicionPadre));
		return convertView;
	}
	
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

	public Context getCont() {
		return cont;
	}

	public void setCont(Context cont) {
		this.cont = cont;
	}

	public ArrayList<String> getDiasPadre() {
		return padre;
	}

	public void setDiasPadre(ArrayList<String> diasPadre) {
		this.padre = diasPadre;
	}

	public ArrayList<ArrayList<String>> getBloqueHijos() {
		return hijos;
	}

	public void setBloqueHijos(ArrayList<ArrayList<String>> bloqueHijos) {
		this.hijos = bloqueHijos;
	}

	public void setInflater(LayoutInflater systemService,
			Activity actividad) {
		// TODO Auto-generated method stub
		
	}
	
}
