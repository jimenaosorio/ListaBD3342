package cl.ejercicios.listabd3342;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import cl.ejercicios.listabd3342.modelo.ComprasDatabaseHelper;
import cl.ejercicios.listabd3342.modelo.ListDeCompras;
import cl.ejercicios.listabd3342.modelo.Producto;


public class ListaProductosActivity extends ListActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        cargarLista();
    }

    public void cargarLista()
    {
        lista=getListView();

        ComprasDatabaseHelper helper=new ComprasDatabaseHelper(this);

        //Recupero la lista de productos desde la base de datos
        List<Producto> productoList=helper.listaProductos();

        //List<Producto> productoList= ListDeCompras.getInstancia().getListaDeCompras();



        ArrayAdapter<Producto> listaAdapter= new ArrayAdapter<Producto>(this,
                android.R.layout.simple_expandable_list_item_1,productoList);
        lista.setAdapter(listaAdapter);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int posicion, long id)
    {
        //Obtengo el elemento de la lista que se seleccionó
        Object o=lista.getItemAtPosition(posicion);

        //Leo el texto de la línea seleccionada
        String linea=o.toString();

        //Separar en un arreglo para obtener el nombre
        String[] separar=linea.split(":");

        Intent intent=new Intent(ListaProductosActivity.this,DetallesActivity.class);

        //Enviamos el nombre a DetallesActivity
        intent.putExtra("nombreProducto",separar[0]);

        // intent.putExtra("idProducto",(int)id);
        startActivityForResult(intent,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1)
        {
            if(resultCode==RESULT_OK)
            {
                cargarLista();
            }
        }
    }
}
