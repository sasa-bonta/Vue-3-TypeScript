/*
package org.example.fancy_project.zNotGonnaUse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("serviceAppointment")
public class ServiceAppointmentController {

    @Autowired
    ServiceAppointmentService serviceAppointmentService;

    @RequestMapping(value = "getAllServiceAppointments", method = RequestMethod.GET)
    public List<ServiceAppointment> getAllServiceAppointments(){
        return serviceAppointmentService.getAllServiceAppointments();
    }

    @RequestMapping(value = "createServiceAppointment", method = RequestMethod.POST)
    public ServiceAppointment createServiceAppointment(@RequestBody ServiceAppointment serviceAppointment){
        return serviceAppointmentService.createServiceAppointment(serviceAppointment);
    }

    @RequestMapping(value = "deleteServiceAppointment/{appointmentId}", method = RequestMethod.DELETE)
    public void deleteServiceAppointment(@PathVariable Integer id){
        serviceAppointmentService.deleteServiceAppointment(id);
    }
}
*/
