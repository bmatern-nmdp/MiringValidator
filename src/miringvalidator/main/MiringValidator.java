/*

    MiringValidator  Semantic Validator for MIRING compliant HML
    Copyright (c) 2014-2015 National Marrow Donor Program (NMDP)

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
package miringvalidator.main;

public class MiringValidator
{
    String xml;
    String report;
    ValidationError[] tier1ValidationErrors;
    ValidationError[] tier2ValidationErrors;
    
    public MiringValidator(String xml)
    {
        this.xml = xml;
        this.report = null;
    }
    
    public String validate()
    {
        //Tier 1
        //tier1ValidationErrors = SchemaValidator.validate(xml, "MiringTier1.xsd");
        tier1ValidationErrors = SchemaValidator.validate(xml, "demo.xsd");
        
        //Tier 2
        //Skip it if we already know it is bad.  Maybe?  Do we want to schematron automatically?
        //Yeah actually don't skip it.  We want to give them the most information we can.
        //if(!ReportGenerator.containsFatalErrors(tier1ValidationErrors))
        {
            tier2ValidationErrors = SchematronValidator.validate(xml, "demo.sch");
            
            //Tier 3 is outside scope for now.  Okay.
            /*if(!containsFatalErrors(tier2ValidationErrors))
            {
                
            }*/
        }

        report = ReportGenerator.generateReport(tier1ValidationErrors, tier2ValidationErrors, "1234","abcd");
        return report;
    }

    public String getXml()
    {
        return xml;
    }

    public void setXml(String xml)
    {
        this.xml = xml;
    }

    public String getReport()
    {
        return report;
    }

}
