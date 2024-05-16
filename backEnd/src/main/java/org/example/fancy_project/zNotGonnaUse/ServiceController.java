/*
package org.example.fancy_project.zNotGonnaUse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("service")
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @RequestMapping(value = "getAllServices", method = RequestMethod.GET)
    public List<Service> getAllServices(){
        return serviceService.getAllServices();
    }

    @RequestMapping(value = "createService", method = RequestMethod.POST)
    public Service createService(@RequestBody Service service){
        return serviceService.createService(service);
    }

    @RequestMapping(value = "deleteService/{serviceId}", method = RequestMethod.DELETE)
    public void deleteService(@PathVariable Integer id){
        serviceService.deleteService(id);
    }
}
*/
