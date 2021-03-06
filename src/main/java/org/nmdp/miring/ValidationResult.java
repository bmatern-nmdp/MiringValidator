/*

    MiringValidator  Semantic Validator for MIRING compliant HML
    Copyright (c) 2015 National Marrow Donor Program (NMDP)

    This library is free software; you can redistribute it and/or modify it
    under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation; either version 3 of the License, or (at
    your option) any later version.

    This library is distributed in the hope that it will be useful, but WITHOUT
    ANY WARRANTY; with out even the implied warranty of MERCHANTABILITY or
    FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
    License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this library;  if not, write to the Free Software Foundation,
    Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307  USA.

    > http://www.gnu.org/licenses/lgpl.html

*/
package org.nmdp.miring;

import java.util.ArrayList;
import java.util.List;

/** 
 * A simple class for storing a Validation Result.  They are sorted by MIRING Rule ID.
*/
public class ValidationResult implements Comparable<ValidationResult>
{
    String errorText;
    String solutionText;
    String miringRule;
    String sampleID;
    List<String> xPaths;
    Severity severity;

    public enum Severity
    {
        //FATAL = Cannot continue validation.  Most likely an HML/XML Structure issue
        //MIRING = A MIRING Rule Validation error. 
        //WARNING = A potential problem, but not enough to reject the document
        //INFO = Purely informational, no problems.
        FATAL, MIRING, WARNING, INFO
    }
    
    /**
     * Constructor for a ValidationError object. 
     *
     * @param errorText Text containing a description of the error.
     * @param severity the severity of a ValidationResult
     */
    public ValidationResult(String errorText, Severity severity)
    {
        this.errorText = errorText;
        this.severity = severity;
        this.solutionText = "";
        this.xPaths = new ArrayList<String>();
        this.miringRule = "";
    }
    
    @Override
    public boolean equals(Object otherObject) 
    {
        ValidationResult otherError = (ValidationResult) otherObject;
        if(
            this.errorText.equals(otherError.errorText)
            && this.severity == otherError.severity
            && this.solutionText.equals(otherError.solutionText)
            && this.xPaths.equals(otherError.xPaths)
            && this.miringRule.equals(otherError.miringRule)
        )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    @Override
    public int compareTo(ValidationResult o)
    {
        //When we compare object, we'll just compare their miringRules.
        //So we can sort the errors by miringRule.
        //If they have the same rule(or no rule) sort by description.
        int miringRuleIDCompare = this.getMiringRule().compareTo(
                ((ValidationResult)(o)).getMiringRule() );
        
        if(miringRuleIDCompare == 0)
        {
            return this.getErrorText().compareTo(((ValidationResult)(o)).getErrorText());
        }
        else
        {
            return miringRuleIDCompare;
        }
    }

    public String getErrorText()
    {
        return errorText;
    }

    public String getSolutionText()
    {
        return solutionText;
    }
    
    public String getMiringRule()
    {
        return miringRule;
    }

    public void setSolutionText(String solutionText)
    {
        this.solutionText = solutionText;
    }

    public Severity getSeverity()
    {
        return severity;
    }
    
    public void setSeverity(Severity severity)
    {
        this.severity = severity;
    }
    
    public List<String> getXPaths()
    {
        return xPaths;
    }

    public void addXPath(String xPath)
    {
        this.xPaths.add(xPath);
    }

    public void setErrorText(String errorText)
    {
        this.errorText = errorText;
    }

    public void setMiringRule(String miringRule)
    {
        this.miringRule = miringRule;
    }
    
    public String getSampleID()
    {
        return sampleID;
    }
    
    public void setSampleID(String sampleID)
    {
        this.sampleID = sampleID;
    }
}
