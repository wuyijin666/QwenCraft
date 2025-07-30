package org.kolar.aicodehelper.ai.rag;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 加载 RAG
 */
@Configuration
public class RagConfig {

    @Resource
    private EmbeddingModel embeddingModel;

    @Resource
    private EmbeddingStore<TextSegment> embeddingStore;

    @Bean
    public ContentRetriever contentRetriever() {
        // --- RAG ---
        // 1. 加载文档
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("src/main/resources/docs");
        // 2. 切割文档 每个文档按照段落分割，最大1000个字符，每次最多重叠 200 个字符
        DocumentByParagraphSplitter documentByParagraphSplitter =
                new DocumentByParagraphSplitter(1000, 200);
        // 3. 自定义文档加载器，将文档转换成向量，保存到向量数据库中
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .documentSplitter(documentByParagraphSplitter)
                // 为了提高文档质量，将每个切割后的文档碎片 TextSegment 添加文档名称作为元信息
                .textSegmentTransformer(textSegment -> TextSegment.from(textSegment.metadata().getString("file_name")
                        + "\n" + textSegment.text(), textSegment.metadata()))
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build();
        // 4. 加载文档
        ingestor.ingest(documents);
        // 5. 自定义内容加载器，进行内容检索
        ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(5) // on each interaction we will retrieve the 5 most relevant segments
                .minScore(0.75) // we want to retrieve segments at least somewhat similar to user query
                .build();
        return contentRetriever;
    }
}



