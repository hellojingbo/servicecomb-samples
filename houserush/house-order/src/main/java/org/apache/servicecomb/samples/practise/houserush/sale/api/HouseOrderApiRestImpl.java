/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.servicecomb.samples.practise.houserush.sale.api;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.samples.practise.houserush.sale.aggregate.HouseOrder;
import org.apache.servicecomb.samples.practise.houserush.sale.aggregate.Sale;
import org.apache.servicecomb.samples.practise.houserush.sale.service.HouseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestSchema(schemaId = "houseOrderApiRest")
@RequestMapping("/")
public class HouseOrderApiRestImpl implements HouseOrderApi {
  @Autowired
  HouseOrderService houseOrderService;

  @PostMapping("sales/{saleId}/house_orders")
  public List<HouseOrder> createHouseOrders(@PathVariable int saleId, @RequestBody List<Integer> houseIds) {
    return houseOrderService.createHouseOrders(saleId, houseIds);
  }

  @PutMapping("house_orders/{houseOrderId}")
  public HouseOrder placeHouseOrder(@RequestHeader int customerId, @PathVariable int houseOrderId) {
    return houseOrderService.placeHouseOrder(customerId, houseOrderId);
  }

  @PutMapping("house_orders/{houseOrderId}/cancel")
  public HouseOrder cancelHouseOrder(@RequestHeader int customerId, @PathVariable int houseOrderId) {
    return houseOrderService.cancelHouseOrder(customerId, houseOrderId);
  }

  @Override
  @PostMapping("sales")
  public Sale createSale(@RequestBody Sale sale) {
    return houseOrderService.createSale(sale);
  }

  @Override
  @GetMapping("sales/{saleId}")
  public Sale findSale(@PathVariable int saleId) {
    return houseOrderService.findSale(saleId);
  }

  @Override
  @PutMapping("sales/{saleId}")
  public Sale updateSale(@PathVariable int saleId, @RequestBody Sale sale) {
    sale.setId(saleId);
    return houseOrderService.updateSale(sale);
  }

  @Override
  @DeleteMapping("sales/{saleId}")
  public void removeSale(@PathVariable int saleId) {
    houseOrderService.removeSale(saleId);
  }

  @Override
  @GetMapping("sales")
  public List<Sale> indexSales() {
    return houseOrderService.indexSales();
  }
}