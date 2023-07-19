package com.daofab.daofab.Controller;

import com.daofab.daofab.Service.ParentService;
import com.daofab.daofab.model.Child;
import com.daofab.daofab.model.Parent;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parents")
public class ParentController {

  @Autowired
  private ParentService parentService;

  @GetMapping
  public ResponseEntity<List<Parent>> getAllParents(
    @RequestParam(defaultValue = "1") int page,
    @RequestParam(defaultValue = "2") int pageSize,
    @RequestParam(defaultValue = "parentId_asc") String sortBy
  ) {
    List<Parent> parents = parentService.getAllParents(page, pageSize, sortBy);

    return new ResponseEntity<>(parents, HttpStatus.OK);
  }

  @GetMapping("/{parentId}/children")
  public ResponseEntity<List<Child>> getChildrenByParentId(
    @PathVariable Long parentId
  ) {
    List<Child> children = parentService.getChildrenByParentId(parentId);
    return new ResponseEntity<>(children, HttpStatus.OK);
  }
}
