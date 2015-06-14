package dungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RoomFactory {

	public RoomFactory(){}
	
	public ArrayList<Room> Run()
	{
		File file = new File("src/dungeon/Rooms");
		File[] list = file.listFiles();
		ArrayList<Room> roomCollection = new ArrayList<Room>();
		
        if(list!=null){
        	for (File fil : list)
        	{
        		Room room = GenerateRoom(fil);
        		
        		if(room == null)
        		{
        			System.out.println("Room generation failed");
        			System.exit(-1);
        		}
        		
        		roomCollection.add(room);
        	}
        }else{
        	System.out.println("Rooms not in " + file.getAbsolutePath());
        	System.exit(-1);
        }
        
        return roomCollection;
	}
	
	public Room GenerateRoom(File file)
	{
		int height;
		int width;
		int doorNumber;
		int[] doors = new int[4];
		
		Scanner scanner = null;

		try {
			scanner = new Scanner(file);
			
			height = scanner.nextInt();
			width = scanner.nextInt();
			doorNumber = scanner.nextInt();
			for(int i = 0; i < 4; i++)
				doors[i] = scanner.nextInt();
			
			return new Room(height, width, doorNumber, doors);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		} finally{
			scanner.close();
		}
		return null;
	}
	
}
