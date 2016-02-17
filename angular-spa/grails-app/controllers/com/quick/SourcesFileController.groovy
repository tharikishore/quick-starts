package com.quick

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SourcesFileController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SourcesFile.list(params), model:[sourcesFileCount: SourcesFile.count()]
    }

    def show(SourcesFile sourcesFile) {
        respond sourcesFile
    }

    @Transactional
    def save(SourcesFile sourcesFile) {
        if (sourcesFile == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (sourcesFile.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sourcesFile.errors, view:'create'
            return
        }

        sourcesFile.save flush:true

        respond sourcesFile, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(SourcesFile sourcesFile) {
        if (sourcesFile == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (sourcesFile.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sourcesFile.errors, view:'edit'
            return
        }

        sourcesFile.save flush:true

        respond sourcesFile, [status: OK, view:"show"]
    }

    @Transactional
    def delete(SourcesFile sourcesFile) {

        if (sourcesFile == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        sourcesFile.delete flush:true

        render status: NO_CONTENT
    }
}
