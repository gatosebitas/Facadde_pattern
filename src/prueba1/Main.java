package prueba1;

import com.google.firebase.FirebaseApp;
//import com.google.api.client.googleapis.auth.oauth2.
import com.google.firebase.FirebaseOptions;
import com.google.auth.oauth2.GoogleCredentials;
//import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.*;
import com.google.auth.*;
import com.google.auth.Credentials;
//import com.google.firebase.storage.StorageReference;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public Main() {
		// TODO Auto-generated constructor stub
		
	}
	public static void main(String[] args) {
		System.out.println("holi");
		Main m =new Main();
		m.listarzonas("Zonas");
	}
	
	
	private static final String ZONA_NODE = "Zonas";

	
	FileInputStream serviceAccount = new FileInputStream("../config/google-services.json");
	FirebaseOptions options = new FirebaseOptions.Builder()
			//.setCredentials(GoogleCredentials.fromStream(serviceAccount))
	    .setDatabaseUrl("https://proyectomap-adbb1.firebaseio.com/proyectomap-adbb1")
	    .build();
	FirebaseApp.initializeApp(options);
	//DatabaseReference ref = FirebaseDatabase.getInstance();
			
			
	
	
	
			
			
    //private GoogleMap mMap;
   private DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://proyectomap-adbb1.firebaseio.com/proyectomap-adbb1");
        //private StorageReference mStorageRef;
    private List<Zona_Cast> list_ZonaCast;
    private Zona_Cast zonaCast;




   
    public void listarzonas(String nodo) {

        //FirebaseDatabase.getInstance().setPersistenceEnabled(false);
        list_ZonaCast = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(ZONA_NODE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Zona zona = snapshot.getValue(Zona.class);
                        String id=zona.getId();
                        Double latitud=Double.parseDouble(zona.getLat());
                        Double longitud= Double.parseDouble(zona.getLongitud());
                        int primaryColor=Integer.parseInt(zona.getPrimaryColor());
                        Double radius= Double.parseDouble(zona.getRadius());
                        int secondColor=Integer.parseInt(zona.getSecondColor());
                        String subtitle=zona.getSubtitle();
                        String title=zona.getTitle();
                        zonaCast = new Zona_Cast(id,latitud,longitud,primaryColor,radius,secondColor,subtitle,title);
                        list_ZonaCast.add(zonaCast);
                        System.out.println(zonaCast);
                        //presenter.showZonas(list_ZonaCast);


                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }



        });


    }

}
