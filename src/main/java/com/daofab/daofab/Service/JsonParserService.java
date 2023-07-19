package com.daofab.daofab.Service;

import com.daofab.daofab.model.Child;
import com.daofab.daofab.model.Parent;
import com.daofab.daofab.util.JsonParserUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class JsonParserService {

  private static final String PARENT_JSON_FILE =
    "C:/Users/balen/Documents/GitHub/Daofab/daofab/src/main/resources/static/data/Parent.json";
  private static final String CHILD_JSON_FILE =
    "C:/Users/balen/Documents/GitHub/Daofab/daofab/src/main/resources/static/data/Child.json";

  public List<Parent> getAllParents(int page, int pageSize, String sortBy) {
    try {
      List<Parent> parents = JsonParserUtil.parseParents(PARENT_JSON_FILE);
      List<Child> children = JsonParserUtil.parseChildren(CHILD_JSON_FILE);
      File gradeList = new File(PARENT_JSON_FILE);
if (!gradeList.exists()) {
    throw new FileNotFoundException("Failed to find file: " + 
        gradeList.getAbsolutePath());
}

      // Assign child objects to their corresponding parent objects based on parentId field
      for (Child child : children) {
        Long parentId = child.getParentId();
        for (Parent parent : parents) {
          if (parent.getId().equals(parentId)) {
            parent.addChild(child);
            break;
          }
        }
      }

      // Calculate total paid amount for each parent
      for (Parent parent : parents) {
        BigDecimal totalPaidAmount = BigDecimal.ZERO;
        for (Child child : parent.getChildren()) {
          totalPaidAmount = totalPaidAmount.add(child.getPaidAmount());
        }
        parent.setTotalPaidAmount(totalPaidAmount);
      }

      // Sort the parents list by parent ID
      Comparator<Parent> parentComparator = Comparator.comparingLong(
        Parent::getId
      );
      Collections.sort(parents, parentComparator);

      // Reverse the list if descending order is requested
      if (sortBy.equals("parentId_desc")) {
        Collections.reverse(parents);
      }

      // Simulate pagination by limiting the number of records returned
      int startIndex = (page - 1) * pageSize;
      int endIndex = Math.min(startIndex + pageSize, parents.size());
      System.out.println(startIndex);
      System.out.println(endIndex);
      return parents.subList(startIndex, endIndex);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<Child> getChildrenByParentId(Long parentId) {
    List<Child> children;
    List<Child> childrenByParentId = new ArrayList<Child>();
    try {
      children = JsonParserUtil.parseChildren(CHILD_JSON_FILE);

      for (Child child : children) {
        if (child.getParentId().equals(parentId)) {
          childrenByParentId.add(child);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return childrenByParentId;
  }
}
