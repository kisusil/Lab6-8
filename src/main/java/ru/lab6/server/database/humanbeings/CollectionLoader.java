package ru.lab6.server.database.humanbeings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.server.database.HibernateSessionFactoryUtil;
import ru.lab6.server.io.IO;
import ru.lab6.server.model.collection.CollectionInfo;
import ru.lab6.server.model.collection.HumanBeingRepository;
import ru.lab6.server.model.collection.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class CollectionLoader {
    private final IO io;
    private static Logger logger = LogManager.getLogger(CollectionLoader.class);

    public CollectionLoader(IO io) {
        this.io = io;
    }

    public Repository load() throws CollectionLoaderException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<HumanBeing> list = session.createQuery("From HumanBeing").list();
        session.close();
        LinkedHashSet<HumanBeing> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.addAll(list);
        return new HumanBeingRepository(new CollectionInfo("HumanBeing", LocalDateTime.now(), linkedHashSet.size()), linkedHashSet);
    }
}
