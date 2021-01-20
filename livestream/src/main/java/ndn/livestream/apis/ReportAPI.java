package ndn.livestream.apis;

import ndn.livestream.entities.ReportEntity;
import ndn.livestream.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ReportAPI {

    @Autowired
    private ReportService reportService;

    @PostMapping("guest/report/report")
    public void report(@RequestBody ReportEntity reportEntity) {
        reportService.report(reportEntity);
    }

    @GetMapping("admin/report/{id}")
    public ReportEntity findById(@PathVariable Long id) {
        return reportService.findById(id);
    }

    @PostMapping("admin/report/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        reportService.delete(id);
    }
    @GetMapping("/admin/report/getAll")
    public List<ReportEntity> getAll() {
        return reportService.getAll();
    }
}
