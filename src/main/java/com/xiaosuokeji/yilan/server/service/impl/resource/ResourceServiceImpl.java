package com.xiaosuokeji.yilan.server.service.impl.resource;

import com.xiaosuokeji.yilan.server.constant.resource.CategoryConsts;
import com.xiaosuokeji.yilan.server.dao.resource.*;
import com.xiaosuokeji.yilan.server.model.resource.*;
import com.xiaosuokeji.yilan.server.service.intf.resource.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资源ServiceImpl<br/>
 * Created by xuxiaowei on 2017/8/4.
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private WebsiteDao websiteDao;

    @Autowired
    private PublicNumberDao publicNumberDao;

    @Autowired
    private AppDao appDao;
    
    @Autowired
    private MiniAppDao miniAppDao;

    @Autowired
    private PeriodicalDao periodicalDao;

    @Autowired
    private PictureDao pictureDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private VideoDao videoDao;

    @Override
    public List<Category> list(Resource resource) {
        List<Category> result = new ArrayList<>();

        Map<String, String> dynamic = new HashMap<>();
        if (resource != null && resource.getName() != null) dynamic.put("name", resource.getName());

        Website website = new Website();
        website.setDynamic(dynamic);
        List<Website> websites = websiteDao.listCombo(website);
        if (websites.size() > 0) {
            Category category = new Category();
            category.setName("网站");
            category.setResources(new ArrayList(websites));
            result.add(category);
        }

        PublicNumber publicNumber = new PublicNumber();
        publicNumber.setDynamic(dynamic);
        List<PublicNumber> publicNumbers = publicNumberDao.listCombo(publicNumber);
        if (publicNumbers.size() > 0) {
            Category category = new Category();
            category.setName("公众号");
            category.setResources(new ArrayList(publicNumbers));
            result.add(category);
        }

        App app = new App();
        app.setDynamic(dynamic);
        List<App> apps = appDao.listCombo(app);
        if (apps.size() > 0) {
            Category category = new Category();
            category.setName("软件");
            category.setResources(new ArrayList(apps));
            result.add(category);
        }

        MiniApp miniApp = new MiniApp();
        miniApp.setDynamic(dynamic);
        List<MiniApp> miniApps = miniAppDao.listCombo(miniApp);
        if (miniApps.size() > 0) {
            Category category = new Category();
            category.setType(CategoryConsts.MINI_APP);
            category.setResources(new ArrayList(miniApps));
            result.add(category);
        }

        Periodical periodical = new Periodical();
        periodical.setDynamic(dynamic);
        List<Periodical> periodicals = periodicalDao.listCombo(periodical);
        if (periodicals.size() > 0) {
            Category category = new Category();
            category.setName("期刊");
            category.setResources(new ArrayList(periodicals));
            result.add(category);
        }

        Picture picture = new Picture();
        picture.setDynamic(dynamic);
        List<Picture> pictures = pictureDao.listCombo(picture);
        if (pictures.size() > 0) {
            Category category = new Category();
            category.setName("图片");
            category.setResources(new ArrayList(pictures));
            result.add(category);
        }

        Book book = new Book();
        book.setDynamic(dynamic);
        List<Book> books = bookDao.listCombo(book);
        if (books.size() > 0) {
            Category category = new Category();
            category.setName("图书");
            category.setResources(new ArrayList(books));
            result.add(category);
        }

        Video video = new Video();
        video.setDynamic(dynamic);
        List<Video> videos = videoDao.listCombo(video);
        if (videos.size() > 0) {
            Category category = new Category();
            category.setName("视频");
            category.setResources(new ArrayList(videos));
            result.add(category);
        }

        return result;
    }
}
