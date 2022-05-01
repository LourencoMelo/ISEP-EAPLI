package eapli.base.warehousemanagement.domain.methods;

import eapli.base.warehousemanagement.domain.warehouse.Accessibility;
import eapli.base.warehousemanagement.domain.warehouse.Begin;
import eapli.base.warehousemanagement.domain.warehouse.Depth;
import eapli.base.warehousemanagement.domain.warehouse.End;
import eapli.base.warehousemanagement.domain.agv.AGVDock;
import eapli.base.warehousemanagement.domain.warehouse.Aisle;
import eapli.base.warehousemanagement.domain.warehouse.Row;
import eapli.base.warehousemanagement.domain.warehouse.WareHousePlant;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportJSONFile {

    /**
     * Import the JSON File
     * @param file file to import
     */
    public WareHousePlant ImportJSONFile(File file){
        try {
            JSONParser jsonparser = new JSONParser();

            FileReader reader = new FileReader(file);

            Object obj = jsonparser.parse(reader);

            JSONObject jsonObject = (JSONObject) obj;

            String description = (String) jsonObject.get("Warehouse");
            int length = ((Long)jsonObject.get("Length")).intValue();
            int width = ((Long)jsonObject.get("Width")).intValue();
            int square = ((Long)jsonObject.get("Square")).intValue();
            String unit = (String) jsonObject.get("Unit");

            /**
             * Importing Array of Aisles
             */
            JSONArray arrayAisles = (JSONArray) jsonObject.get("Aisles");
            List<Aisle> aisleList = new ArrayList<>();
            for(int i=0; i <arrayAisles.size(); i++){
                JSONObject aisle = (JSONObject) arrayAisles.get(i);
                long idAisle = ((Long)aisle.get("Id"));
                /**
                 * Importing begin
                 */
                JSONObject beginAisle = (JSONObject) aisle.get("begin");
                int beginLsquareAisle = ((Long)beginAisle.get("lsquare")).intValue();
                int beginWsquareAisle = ((Long)beginAisle.get("wsquare")).intValue();
                Begin beginAisleObj = new Begin(beginLsquareAisle, beginWsquareAisle);

                /**
                 * Importing end
                 */
                JSONObject endAisle = (JSONObject) aisle.get("end");
                int endLsquareAisle =  ((Long)endAisle.get("lsquare")).intValue();
                int endWsquareAisle = ((Long)endAisle.get("wsquare")).intValue();
                End endAisleObj = new End(endLsquareAisle, endWsquareAisle);
                /**
                 * Importing depth Aisle
                 */
                JSONObject depthAisle = (JSONObject) aisle.get("depth");
                int depthLsquareAisle = ((Long)depthAisle.get("lsquare")).intValue();
                int depthWsquareAisle = ((Long) depthAisle.get("wsquare")).intValue();
                Depth depthAisleObj = new Depth(depthLsquareAisle, depthWsquareAisle);

                String accessibilityAisle = (String) aisle.get("accessibility");
                Accessibility accessibilityAisleObj = new Accessibility(accessibilityAisle);

                /**
                 * Importing Array of Rows
                 */
                JSONArray arrayRows = (JSONArray) aisle.get("rows");
                List<Row> rowList = new ArrayList<>();
                for(int j=0; j < arrayRows.size(); j++){
                    JSONObject row = (JSONObject) arrayRows.get(j);
                    long idRow = ((Long) row.get("Id"));
                    /**
                     * Importing begin
                     */
                    JSONObject beginRow = (JSONObject) row.get("begin");
                    int beginLsquareRow = ((Long) beginRow.get("lsquare")).intValue();
                    int beginWsquareRow = ((Long) beginRow.get("wsquare")).intValue();
                    Begin beginRowObj = new Begin(beginLsquareRow, beginWsquareRow);
                    /**
                     * Importing end
                     */
                    JSONObject endRow = (JSONObject) row.get("end");
                    int endLsquareRow = ((Long) endRow.get("lsquare")).intValue();
                    int endWsquareRow = ((Long) endRow.get("wsquare")).intValue();
                    End endRowObj = new End(endLsquareRow, endWsquareRow);

                    int shelves = ((Long) row.get("shelves")).intValue();

                    Row rowObj = new Row(idRow,beginRowObj, endRowObj, shelves);
                    rowList.add(rowObj);
                }
                Aisle aisleObj = new Aisle(idAisle,beginAisleObj, endAisleObj, depthAisleObj,accessibilityAisleObj);
                aisleObj.setListRow(rowList);
                aisleList.add(aisleObj);
            }

            /**
             * Importing Array of AGVDocks
             */
            JSONArray arrayAGVDocks = (JSONArray) jsonObject.get("AGVDocks");
            List<AGVDock> agvDockList = new ArrayList<>();
            for(int i=0 ; i< arrayAGVDocks.size(); i++){
                JSONObject agvDock = (JSONObject) arrayAGVDocks.get(i);
                String idAgvDock = ((String) agvDock.get("Id"));
                /**
                 * Importing begin
                 */
                JSONObject beginAGVDock = (JSONObject) agvDock.get("begin");
                int beginLsquareDock = ((Long) beginAGVDock.get("lsquare")).intValue();
                int beginWsquareDock = ((Long) beginAGVDock.get("wsquare")).intValue();
                Begin beginAGVDockObj = new Begin(beginLsquareDock, beginWsquareDock);

                /**
                 * Importing end
                 */
                JSONObject endAGVDock = (JSONObject) agvDock.get("end");
                int endLsquareDock = ((Long) endAGVDock.get("lsquare")).intValue();
                int endWsquareDock = ((Long) endAGVDock.get("wsquare")).intValue();
                End endAGVDockObj = new End(endLsquareDock,endWsquareDock);
                /**
                 * Importing depth
                 */
                JSONObject depthAGVDock = (JSONObject) agvDock.get("depth");
                int depthLsquareDock = ((Long) depthAGVDock.get("lsquare")).intValue();
                int depthWsquareDock = ((Long) depthAGVDock.get("wsquare")).intValue();
                Depth depthAGVDockObj = new Depth(depthLsquareDock, depthWsquareDock);

                String accessibilityAGVDock = (String) agvDock.get("accessibility");
                Accessibility accessibilityAGVDockObj = new Accessibility(accessibilityAGVDock);

                AGVDock agvDockObj = new AGVDock(idAgvDock,beginAGVDockObj, endAGVDockObj, depthAGVDockObj, accessibilityAGVDockObj);
                agvDockList.add(agvDockObj);
            }

            WareHousePlant wareHousePlant = new WareHousePlant(description,length,width,square,unit,
                    aisleList,agvDockList);

            return wareHousePlant;

        }catch (IllegalArgumentException illegalArgumentException){
            System.out.println("IllegalArgumentException ! Something went wrong on type of values on file");
        }catch (ParseException parseException){
            System.out.println("Error in parsing the JSON objects");
        }catch(FileNotFoundException fileNotFoundException){
            System.out.println("File not found! \nSomething went wrong with the import!");
        }catch (IOException ioException){
            System.out.println("Error in the IOException \n Error in the parser to Object");
        }catch (Exception exception){
            exception.printStackTrace();
            System.out.println("Unexpected error detected!");
        }

        return null;
    }
}
