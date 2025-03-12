package com.example.consoleApp.controller;

import com.example.consoleApp.Gson.GsonWriterRepositoryImp;
import com.example.consoleApp.model.Status;
import com.example.consoleApp.model.Writer;
import com.example.consoleApp.repository.WriterRepository;

import java.util.ArrayList;
import java.util.List;

public class WriterController {
    private final WriterRepository writerRepository;

    public WriterController(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    public void createWriter(Long id, String firstName, String lastName) {
        Writer writer = new Writer(id, firstName, lastName, new ArrayList<>(), Status.ACTIVE);
        writerRepository.save(writer);
        System.out.println("writer sohranen" + writer.getFirstName());
    }

    public void saveWriterToFile() {
        List<Writer> writers = writerRepository.getAll();
        ((GsonWriterRepositoryImp) writerRepository).writeToFile(writers);
        System.out.println("Writers sohraneni");
    }

//    public void deleteWriter(Long id) {
//        List<Writer> writersList = writerRepository.getAll();
//
//        for(int i = 0; i < writersList.size(); i++) {
//           Writer writer = writersList.get(i);
//
//           if(writer.getId().equals(id)) {
//               writer.setStatus(Status.DELETED);
//               writerRepository.save(writer);
////               System.out.println("Index ydalennogo writera " + id);
//               return;
//           }
//        }
////        System.out.println("Writer ne naiden s indeksom " + id);
//    }
    public void deleteWriter(Long id) {
        List<Writer> writersList = writerRepository.getAll();

        for(int i = 0; i <writersList.size(); i++) {
            Writer writer = writersList.get(i);

            if(writer.getId().equals(id)) {
                writer.setStatus(Status.DELETED);
                writerRepository.saveAll(writersList);
                System.out.println("Writer with id " + id + " pome4en na Ydalenie");
                return;
            }
        }
        System.out.println("Writer with id " + id + " not found");

    }

//    public void updateWriter(Long id, String firstName, String lastName, Status) {
//        List<Writer> writerList = writerRepository.getAll();
//
//        for (Writer writer : writerList) {
//
//            if(writer.getId().equals(id)) {
//                writer.setFirstName(firstName);
//                writer.setLastName(lastName);
//                writer.setStatus(Status.ACTIVE);
//
//                ((GsonWriterRepositoryImp) writerRepository).writeToFile(writerList);
//                System.out.println("Writer updated: " + writer.getFirstName() + " " + writer.getLastName() + " " + writer.getStatus());
//                return;
//            }
//        }
//        System.out.println("Writer with id: " + id + " not found.");
//    }

}
