package org.uengine.five.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.uengine.modeling.resource.IContainer;
import org.uengine.modeling.resource.IResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by uengine on 2017. 11. 11..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Relation(value = "definition", collectionRelation = "definitions")
public class DefinitionResource extends ResourceSupport {

    String name;

    public DefinitionResource(){}

    public DefinitionResource(IResource resource1) throws Exception {
        setName(resource1.getName());
        setDirectory(resource1 instanceof IContainer);


        String relativePath = resource1.getPath().substring(DefinitionServiceImpl.resourceRoot.length()+1);

        setPath(relativePath);

        add(
                linkTo(
                        methodOn(DefinitionServiceImpl.class)
                                .getDefinition(
                                     relativePath
                                )
                ).withSelfRel()
        );

        if(!isDirectory()) {
            add(
                    linkTo(
                            methodOn(DefinitionServiceImpl.class)
                                    .getRawDefinition(
                                            relativePath
                                    )
                    ).withRel("raw")
            );
            add(
                    ControllerLinkBuilder.linkTo(
                            methodOn(InstanceService.class)
                                    .runDefinition(
                                            relativePath
                                    )
                    ).withRel("instantiation")
            );

        }


    }

    public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

    boolean directory;
        public boolean isDirectory() {
            return directory;
        }
        public void setDirectory(boolean directory) {
            this.directory = directory;
        }

    public String path;
        public String getPath() {
            return path;
        }
        public void setPath(String path) {
            this.path = path;
        }


}
