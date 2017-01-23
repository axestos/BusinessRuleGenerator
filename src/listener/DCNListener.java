package listener;

import oracle.jdbc.dcn.DatabaseChangeEvent;
import oracle.jdbc.dcn.DatabaseChangeListener;

public class DCNListener implements DatabaseChangeListener{
	DBChangeNotification dbchange;
	
	DCNListener(DBChangeNotification db){
		dbchange = db;
		}

	@Override
	//IDK wat dit doet, even nakijken nog
	public void onDatabaseChangeNotification(DatabaseChangeEvent event) {
		Thread t = Thread.currentThread();
	    System.out.println("DCNListener: got an event ("+this+" running on thread "+t+")");
	    System.out.println(event.toString());
	    synchronized( dbchange ){ dbchange.notify();}
	}

}
