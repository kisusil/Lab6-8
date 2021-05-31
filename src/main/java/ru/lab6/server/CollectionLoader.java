package ru.lab6.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.server.io.IO;
import ru.lab6.server.model.CollectionInfo;
import ru.lab6.server.model.HumanBeingRepository;
import ru.lab6.server.model.Repository;

import java.time.LocalDateTime;
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
        LinkedHashSet<HumanBeing> linkedHashSet = (LinkedHashSet<HumanBeing>)  session.createQuery("From HumanBeing").list();
        session.close();
        return new HumanBeingRepository(new CollectionInfo("HumanBeing", LocalDateTime.now(), linkedHashSet.size()), linkedHashSet);
    }
}
