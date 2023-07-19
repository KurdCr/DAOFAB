package com.daofab.daofab.Service;

import com.daofab.daofab.model.Child;
import com.daofab.daofab.model.Parent;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParentService {

  @Autowired
  private JsonParserService jsonParserService;

  public List<Child> getChildrenByParentId(Long parentId) {
    return jsonParserService.getChildrenByParentId(parentId);
  }

  public List<Parent> getAllParents(int page, int pageSize, String sortBy) {
    return jsonParserService.getAllParents(page, pageSize, sortBy);
  }
}
