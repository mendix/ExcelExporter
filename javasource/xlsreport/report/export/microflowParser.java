package xlsreport.report.export;

import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.systemwideinterfaces.core.IContext;
import java.util.HashMap;
import java.util.Map;

public class microflowParser {

    public String microflowParser (IContext context, String mfName, String value) throws CoreException {

        //grab the value of the cell
        String param = value;

        //retrieve the parameter name of the mf
        String inputParam = Core.getInputParameters(mfName).keySet().toArray()[0].toString();

        //create a parameter to pass to the mf
        Map<String,Object> parameters = new HashMap<String, Object>();
        parameters.put(inputParam, param);
        //set the new value for data
       String returnValue =  Core.execute(context, mfName, parameters);

       return returnValue;

    }
}
