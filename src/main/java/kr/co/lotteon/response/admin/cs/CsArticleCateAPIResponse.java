package kr.co.lotteon.response.admin.cs;


import kr.co.lotteon.entity.admin.cs.CsArticleCateEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CsArticleCateAPIResponse {

    private   Map<String, Map<String, List<String>>>   menuMap = new HashMap<>();



    /**
     * csArticleIds는 반드시 menu1으로 정렬되어 들어와야한다.
     * @param csArticleIds
     */
    public CsArticleCateAPIResponse(List<CsArticleCateEntity> csArticleIds){
        String cateName = "";
        String menu1Name = "";
        for (CsArticleCateEntity pair : csArticleIds) {
            if(cateName.isEmpty()){
                cateName = pair.getCate();
            }
            String curCateName = pair.getCate();
            if(menuMap.containsKey(curCateName)){
                Map<String, List<String>> menu1Andmenu2 = new HashMap<>();
                if(menu1Name.isEmpty()){
                    menu1Name = pair.getMenu1();
                    String curMenu1Name = pair.getMenu1();
                    if(menu1Andmenu2.containsKey(curMenu1Name)){
                        List<String> menu2s = menu1Andmenu2.get(curMenu1Name);
                        menu2s.add(pair.getMenu2());
                    }else{
                        List<String> newMenu2s = new ArrayList<>();
                        newMenu2s.add(pair.getMenu2());
                        menu1Andmenu2.put(curMenu1Name, newMenu2s);
                    }//menu2 end
                }

            }else{
                Map<String, List<String>> menu1Andmenu2 = new HashMap<>();
                if(menu1Name.isEmpty()){
                    menu1Name = pair.getMenu1();
                    String curMenu1Name = pair.getMenu1();
                    if(menu1Andmenu2.containsKey(curMenu1Name)){
                        List<String> menu2s = menu1Andmenu2.get(curMenu1Name);
                        menu2s.add(pair.getMenu2());
                    }else{
                        List<String> newMenu2s = new ArrayList<>();
                        newMenu2s.add(pair.getMenu2());
                        menu1Andmenu2.put(curMenu1Name, newMenu2s);
                    }//menu2 end
                }
                menuMap.put(curCateName,menu1Andmenu2);
            }





//            if (this.menu1Name.equals("")) {
//                this.menu1Name = pair.getCate();
//            }
//            String curMenu1 = pair.getMenu1();
//            if (menuMap.containsKey(curMenu1)) {
//                List<String> menu2s = menuMap.get(curMenu1);
//                menu2s.add(pair.getMenu2());
//            }else{
//                List<String> newMenu2s = new ArrayList<>();
//                newMenu2s.add(pair.getMenu2());
//                menuMap.put(curMenu1, newMenu2s);
//            }
        }
    }
}
