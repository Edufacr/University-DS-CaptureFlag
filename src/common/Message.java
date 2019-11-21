package common;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Message {
    private int Type;
    private JSONObject json;
    private final String TYPE_ID_NAME = "Type";

    public Message(int pType) {
        this.Type = pType;
        json = new JSONObject();
        addField(TYPE_ID_NAME, pType);
    }

    public Message(String pJson) {
        try
        {
            json = new JSONObject(pJson);
            this.Type = json.getInt(TYPE_ID_NAME);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void addField(String pKey, String pValue) {
        try
        {
            json.put(pKey, pValue);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void addField(String pKey, int pValue) {
        try
        {
            json.put(pKey, pValue);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void addField(String pKey, float pValue) {
        try
        {
            json.put(pKey, pValue);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void addField(String pKey, boolean pValue) {
        try
        {
            json.put(pKey, pValue);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void addField(String pKey, ArrayList<ArrayList<String>> pValue) {
    	JSONArray array = new JSONArray();
    	for (int arrayIndex = 0; arrayIndex < pValue.size(); arrayIndex++) 
    	{
    		for (String string : pValue.get(arrayIndex))
    	        array.put(string);
    	}
    	try 
    	{
    	    json.put("result", array);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    public String getValue(String pKey) {
        String result = "";
        try
        {
            result = json.get(pKey).toString();
        }
        catch (Exception ex)
        {
            result = "";
        }
        return result;
    }

    public boolean getBoolValue(String pKey) {
        boolean result = false;
        try
        {
            result = (boolean)json.get(pKey);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return result;
    }

    public int getIntValue(String pKey) {
        int result = -1;
        try
        {
            result = (int)json.get(pKey);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return result;
    }

    public float getFloatValue(String pKey) {
        float result = 0.0f;
        try
        {
            result = (float)json.get(pKey);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<ArrayList<String>> getStringArrayValue(String pKey) {
    	ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
    
			try
	        {
				JSONArray jsonArray = json.getJSONArray(pKey);
		        
		        ArrayList<String> temporaryArray = new ArrayList<String>();
		        JSONArray temporaryJsonArray;
				for (int arrayIterator = 0; arrayIterator < jsonArray.length(); arrayIterator++) 
				{
					temporaryJsonArray = jsonArray.getJSONArray(arrayIterator);
					for (int insideArrayIterator = 0; arrayIterator < temporaryJsonArray.length(); insideArrayIterator++) 
					{
						temporaryArray.add(temporaryJsonArray.getString(insideArrayIterator));
					}
					result.add(temporaryArray);
				}
	        }
	        catch (Exception ex)
	        {
	            ex.printStackTrace();
	        }
    	
    	return result;
    }
    
    public ArrayList<ArrayList<Integer>> getIntegerArrayValue(String pKey) {
    	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    
			try
	        {
				JSONArray jsonArray = json.getJSONArray(pKey);
		        
		        ArrayList<Integer> temporaryArray = new ArrayList<Integer>();
		        JSONArray temporaryJsonArray;
				for (int arrayIterator = 0; arrayIterator < jsonArray.length(); arrayIterator++) 
				{
					temporaryJsonArray = jsonArray.getJSONArray(arrayIterator);
					for (int insideArrayIterator = 0; arrayIterator < temporaryJsonArray.length(); insideArrayIterator++) 
					{
						temporaryArray.add(Integer.parseInt(temporaryJsonArray.getString(insideArrayIterator)));
					}
					result.add(temporaryArray);
				}
	        }
	        catch (Exception ex)
	        {
	            ex.printStackTrace();
	        }
    	
    	return result;
    }

    public String getJSonString() {
        return json.toString();
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }
}
