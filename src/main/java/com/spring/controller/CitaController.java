package com.spring.controller;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.model.dao.DaoCita;
import com.spring.model.dao.DaoMascota;
import com.spring.model.dao.DaoVeterinario;
import com.spring.model.entity.Cita;
import com.spring.model.entity.Mascota;
import com.spring.model.entity.User;
import com.spring.model.entity.Veterinario;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
public class CitaController {

    @Autowired
    private DaoCita dao;

    @Autowired
    private DaoMascota daoMascota;

    @Autowired
    private DaoVeterinario daoVeterinario;

    @GetMapping("/cita")
    public String admin(Model modelo) {
        List<Cita> lista = dao.listar("");
        modelo.addAttribute("citas", lista);
        modelo.addAttribute("total", dao.total());

        return "admin";
    }

    @GetMapping("/createCita")
    public String createCita(Model modelo) {
        modelo.addAttribute("cita", new Cita());

        List<Mascota> listaMascotas = daoMascota.listar("");
        modelo.addAttribute("mascotas", listaMascotas);

        List<Veterinario> listaVeterinarios = daoVeterinario.listar("");
        modelo.addAttribute("veterinarios", listaVeterinarios);
        return "Cita/formCita";
    }

    @PostMapping("/saveCita")
    public String saveCita(Cita obj) {
        if (dao.insertar(obj)) {
            return "redirect:/admin";
        } else {
            return "redirect:/createCita";
        }
    }

    @GetMapping("/editCita/{id}")
    public String editCita(Model modelo, @PathVariable int id) {
        Cita cita = dao.buscar(id);
        modelo.addAttribute("cita", cita);

        List<Mascota> listaMascotas = daoMascota.listar("");
        modelo.addAttribute("mascotas", listaMascotas);

        List<Veterinario> listaVeterinarios = daoVeterinario.listar("");
        modelo.addAttribute("veterinarios", listaVeterinarios);
        return "Cita/edit";
    }

    @PostMapping("/updateCita/{id}")
    public String updateCita(Cita obj, @PathVariable int id) {
        obj.setId(id);
        if (dao.actualizar(obj)) {
            return "redirect:/cita";
        } else {
            return "redirect:/editCita";
        }
    }

    @GetMapping("/deleteCita/{id}")
    public String deleteCita(@PathVariable Integer id) {
        if (dao.eliminar(id)) {
            return "redirect:/admin";
        } else {
            return "redirect:/admin";
        }
    }

    @GetMapping("/reporteCita/{id}")
    public ResponseEntity<ByteArrayResource> invoice(Model modelo, @PathVariable int id) {
        return exportReporte(id);
    }

    @NotNull
    public ResponseEntity<ByteArrayResource> exportReporte(int id) {
        Optional<Cita> optionalCita = dao.findById(id);
        if (optionalCita.isPresent()) {
            try {
                final Cita cita = optionalCita.get();

                final File file = ResourceUtils.getFile("classpath:exportInvoice.jasper");
                final File imglogo = ResourceUtils.getFile("classpath:image/MetroPet.jpeg");

                final JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);

                final HashMap<String, Object> parameters = new HashMap<>();

                parameters.put("nombreCliente", cita.getCliente_nombre());
                parameters.put("imgLogo", new FileInputStream(imglogo));

                List<Cita> listaCita = new ArrayList<Cita>();
                listaCita.add(cita);

                parameters.put("dsInvoice", new JRBeanCollectionDataSource((Collection<?>) listaCita));

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                        new JREmptyDataSource());

                byte[] reporte = JasperExportManager.exportReportToPdf(jasperPrint);
                String sdf = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                StringBuilder stringBuilder = new StringBuilder().append("reporteCita:");

                ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                        .filename(stringBuilder.append(cita.getId())
                                .append("generateDate:")
                                .append(sdf)
                                .append(".pdf")
                                .toString())
                        .build();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentDisposition(contentDisposition);
                return ResponseEntity.ok().contentLength((long) reporte.length)
                        .contentType(MediaType.APPLICATION_PDF)
                        .headers(headers).body(new ByteArrayResource(reporte));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return ResponseEntity.noContent().build();
        }
        return null;
    }
}
