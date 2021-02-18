package br.com.superacaobikes.admin.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import javassist.bytecode.stackmap.BasicBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    private final AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucketName;

    public S3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public void uploadFile(String localfilePath){
        try {
            LOG.info("Iniciando Upload.");
            File file = new File(localfilePath);
            s3client.putObject(new PutObjectRequest(bucketName, "teste.jpg", file));
            LOG.info("Upload finalizado com sucesso.");
        }
        catch (AmazonServiceException e){
            LOG.info("AmazonServiceException: " + e.getErrorMessage());
            LOG.info("Status code: " + e.getErrorCode());
        }
        catch (AmazonClientException e){
            LOG.info("AmazonServiceException: " + e.getMessage());
        }
    }
}
