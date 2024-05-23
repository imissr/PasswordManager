import java.util.*;

public class EntryStore {
    private Map<Integer,Entry> entries = new HashMap<>();
    int id = 0;



    EntryStore(){

    }

    public void putEntry( Entry entry){
        entries.put( assignId(), new Entry(entry.getTitle(), entry.getUserName(),entry.getPassword()));
    }



    public boolean deleateEntry(int id){
        if(entries.remove(id) == null){
            return false;
        }else{
            return true;
        }
    }

    public Entry returnValueOfInex(int index){
        Set<Integer> keySet = entries.keySet();
        Integer[] keyArray
                = keySet.toArray(new Integer[keySet.size()]);

        return entries.get(keyArray[index - 1]);
    }


    public int assignId(){
        return id++;
    }




}
