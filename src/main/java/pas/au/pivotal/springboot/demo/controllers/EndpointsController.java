package pas.au.pivotal.springboot.demo.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pas.au.pivotal.springboot.demo.Utils;

@Controller
public class EndpointsController
{
    private static Log logger = LogFactory.getLog(EndpointsController.class);

    @RequestMapping(value = "/endpoints", method = RequestMethod.GET)
    public String endpointsPage(Model model) throws Exception
    {
        logger.info("Invoking Endpoints Controller...");

        model.addAttribute("appIndex", Utils.applicationIndex());
        model.addAttribute("dbservice", Utils.getDBService());

        model.addAttribute("propertyMap", Utils.jvmPropertyMap());

        model.addAttribute("vcapServices", Utils.getEnvMap("VCAP_SERVICES"));
        model.addAttribute("vcapApplication", Utils.getEnvMap("VCAP_APPLICATION"));

        return "endpoints";
    }
}
