package com.sma.delivery.service.comments;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import com.sma.delivery.dao.comments.CommentsDaoImpl;
import com.sma.delivery.dao.comments.ICommentsDao;
import com.sma.delivery.dao.establishments.IEstablishmentsDao;
import com.sma.delivery.dao.user.IUserDao;
import com.sma.delivery.domain.comments.CommentsDomain;

import com.sma.delivery.dto.comments.CommentsDTO;
import com.sma.delivery.dto.comments.CommentsResult;
import com.sma.delivery.dto.products.ProductsDTO;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class CommentsServiceImpl extends BaseServiceImpl<CommentsDTO, CommentsDomain, CommentsDaoImpl, CommentsResult> implements ICommentsService {
	@Autowired
	private ICommentsDao commentsDao;
	
	@Autowired
	private IEstablishmentsDao establishmentsDao;
	@Autowired
	private IUserDao userDao;
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'comments_' + #comment.id", condition = "#dto.id!=null")
	public CommentsDTO save(CommentsDTO dto) {
		final CommentsDomain domain = convertDtoToDomain(dto);
		final CommentsDomain commentsDomain = commentsDao.save(domain);
		final CommentsDTO newDto = convertDomainToDto(domain);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("comments_" + domain.getId(), newDto);
		}
		return convertDomainToDto(commentsDomain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'comments_' + #id")
	public CommentsDTO getById(Integer id) {
		final CommentsDomain domain = commentsDao.getById(id);
		return convertDomainToDto(domain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'comments_' + #id")
	public CommentsResult getAll() {
		final List<CommentsDTO> comments = new ArrayList<>();
		for (CommentsDomain domain : commentsDao.findAll()) {
			final CommentsDTO dto = convertDomainToDto(domain);
			comments.add(dto);
		}
		final CommentsResult commentsResult = new CommentsResult();
		commentsResult.setComments(comments);
		return commentsResult;
	}

	@Override
	protected CommentsDTO convertDomainToDto(CommentsDomain domain) {
		final CommentsDTO dto = new CommentsDTO();
		dto.setId(domain.getId());
		dto.set_title(domain.getTitle());
		dto.set_content(domain.get_content());
		dto.set_deleted(domain.get_deleted());
		dto.set_establishments_id(domain.get_establisments().getId());
		dto.set_users_id(domain.getUser().getId());

		return dto;
	}

	@Override
	protected CommentsDomain convertDtoToDomain(CommentsDTO dto) {
		final CommentsDomain domain = new CommentsDomain();
		domain.setId(dto.getId());
		domain.setTitle(dto.get_title());
		domain.set_content(dto.get_content());
		domain.set_deleted(dto.get_deleted());
		domain.set_establisments(establishmentsDao.getById(dto.get_establishments_id()));
		domain.setUser(userDao.getById(dto.get_users_id()));
		return domain;
	}

	@Override
	@Transactional
	public CommentsResult get(Integer page, Integer tamPag) {
		final List<CommentsDTO> clientss = new ArrayList<>();
		for (CommentsDomain domain : commentsDao.findByParams(page, tamPag)) {
			final CommentsDTO client = convertDomainToDto(domain);
			clientss.add(client);
		}

		final CommentsResult clientResult = new CommentsResult();
		clientResult.setComments(clientss);
		return clientResult;
		}
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'comments_' + #dto.id")
	public CommentsDTO update(CommentsDTO dto) {
		final CommentsDomain clientDomain = convertDtoToDomain(dto);
		final CommentsDomain client = commentsDao.update(clientDomain);
		final CommentsDTO newDto = convertDomainToDto(client);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("comments_" + client.getId(), newDto);
		}
		return convertDomainToDto(client);
	}
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'comments_' + #dto.id")
	public void delete(CommentsDTO dto) {
		final CommentsDomain commentsDomain = convertDtoToDomain(dto);
		commentsDao.delete(commentsDomain);	
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'busqueda_com' + #text")
	public CommentsResult find(String text) {
		final List<CommentsDTO> comments = new ArrayList<>();
		for (CommentsDomain domain : commentsDao.find(text)) {
			final CommentsDTO user = convertDomainToDto(domain);
			comments.add(user);
		}

		final CommentsResult commentsResult = new CommentsResult();
		commentsResult.setComments(comments);
		return commentsResult;
	}
	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'pagina_com' + #page + #size")
	public CommentsResult getAll(Integer page,Integer size) {
		final List<CommentsDTO> comments = new ArrayList<>();
		for (CommentsDomain domain : commentsDao.findAll(page,size)) {
			final CommentsDTO dto = convertDomainToDto(domain);
			comments.add(dto);
		}
		final CommentsResult commentsResult = new CommentsResult();
		commentsResult.setComments(comments);
		return commentsResult;
	}

}

