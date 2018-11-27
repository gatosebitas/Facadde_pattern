package prueba1;

import java.util.List;

public class Facade {

	private static final String Vehiculos_NODE = "Vehiculos";
	private static final String Puntos_interes_NODE = "Puntos_interes";
   
    private DatabaseReference databaseReference;
    private StorageReference mStorageRef;
    
    private List<Vehiculo> list_Vehiculo;
    private Vehiculo vehiculo2;
    
    private List<Coordenada_Poligono> list_coordenada;
    private Coordenada_Poligono coordenada2;
    
    private List<Punto_Interes> lis_puntos_interes;
    private Punto_Interes puntos_interes2;
    
    
    public List<Vehiculo> listarVehiculo(String nodo) {

        //FirebaseDatabase.getInstance().setPersistenceEnabled(false);
        list_Vehiculo = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(Vehiculos_NODE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Vehiculo_Json vehiculo= snapshot.getValue(Vehiculo_Json.class);
                        String id=vehiculo.getId();
                        Double latitud=Double.parseDouble(vehiculo.getLatitud());
                        Double longitud= Double.parseDouble(vehiculo.getLongitud());
                        String fecha=vehiculo.getFecha();
                        Double velocidad= Double.parseDouble(vehiculo.getVelocidad());
                        
                        vehiculo2 = new Vehiculo(id,latitud,longitud,fecha,velocidad);
                        list_Vehiculo.add(vehiculo2);
                        
                       

                    }
                }
                return list_Vehiculo;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }



        });


    }

}
