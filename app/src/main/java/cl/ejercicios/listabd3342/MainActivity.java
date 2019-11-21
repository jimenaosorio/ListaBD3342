package cl.ejercicios.listabd3342;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import cl.ejercicios.listabd3342.modelo.ComprasDatabaseHelper;
import cl.ejercicios.listabd3342.modelo.ListDeCompras;
import cl.ejercicios.listabd3342.modelo.Producto;

public class MainActivity extends AppCompatActivity {
   // private ListDeCompras lista= ListDeCompras.getInstancia();
    private ComprasDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void verLista(View view)
    {
        //ArrayList<Producto> productos=lista.getListaDeCompras();
        /*
        if(productos.size()>0) {
            Intent intent = new Intent(this, ListaProductosActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"La lista de compras está vacía",
                    Toast.LENGTH_SHORT).show();
        }
        */
        helper=new ComprasDatabaseHelper(this);
        try{
            ArrayList<Producto> productos=(ArrayList<Producto>) helper.listaProductos();
            Intent intent = new Intent(this, ListaProductosActivity.class);
            startActivity(intent);
        }catch (Exception ex)
        {
            Toast.makeText(this,"La lista está vacía",Toast.LENGTH_SHORT).show();
        }
    }

    public void ingresarNuevo(View view)
    {
        Intent intent=new Intent(this, NuevoProductoActivity.class);
        startActivity(intent);
    }

    public void eliminarComprados(View view)
    {
        /*
        lista.eliminarComprados();
        Toast.makeText(this,"Se eliminaron los productos comprados",
                Toast.LENGTH_SHORT).show();
                */
        helper=new ComprasDatabaseHelper(this);
        String msg=helper.eliminarComprados();
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
