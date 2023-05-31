package com.colis.batch.tasklet;

import com.colis.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

@RequiredArgsConstructor
public class SendProcTasklet implements Tasklet {

    private final PostService postService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)  {
        postService.DesactivePost();
        return RepeatStatus.FINISHED;
    }
}
